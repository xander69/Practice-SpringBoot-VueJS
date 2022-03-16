package org.xander.practice.webapp.vuejs.service;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xander.practice.webapp.vuejs.entity.Message;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.model.MetaObject;
import org.xander.practice.webapp.vuejs.repository.MessageRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {

  private static final String URL_PATTERN = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.,~#?&//=]*)";
  private static final String IMAGE_PATTERN = "\\.(jpeg|jpg|gif|png)$";
  private static final Pattern URL_REGEX = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
  private static final Pattern IMAGE_REGEX = Pattern.compile(IMAGE_PATTERN, Pattern.CASE_INSENSITIVE);

  private final MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public List<Message> getAllMessages() {
    return messageRepository.findAll();
  }

  public Message addMessage(Message message, User author) {
    message.setAuthor(author);
    message.setCreateDt(new Date());
    message.setChangeDt(new Date());
    fillMeta(message);
    return messageRepository.save(message);
  }

  public Message updateMessage(Message messageFromDb, Message message) {
    messageFromDb.setText(message.getText());
    messageFromDb.setChangeDt(new Date());
    fillMeta(messageFromDb);
    return messageRepository.save(messageFromDb);
  }

  public void removeMessage(Message message) {
    messageRepository.delete(message);
  }

  @SneakyThrows
  private void fillMeta(Message message) {
    String text = message.getText();
    Matcher matcher = URL_REGEX.matcher(text);
    if (matcher.find()) {
      String url = text.substring(matcher.start(), matcher.end());
      message.setLink(url);
      matcher = IMAGE_REGEX.matcher(url);
      if (matcher.find()) {
        message.setLinkCover(url);
      } else if (!url.contains("youtu")) {
        MetaObject meta = getMeta(url);
        message.setLinkTitle(meta.getTitle());
        message.setLinkDescription(meta.getDescription());
        message.setLinkCover(meta.getCover());
      }
    }
  }

  private MetaObject getMeta(String url) throws IOException {
    Document doc = Jsoup.connect(url).get();
    Elements title = doc.select("meta[name$=title],meta[property$=title]");
    Elements description = doc.select("meta[name$=description],meta[property$=description]");
    Elements cover = doc.select("meta[name$=image],meta[property$=image]");
    return new MetaObject(
        getContent(title.first()),
        getContent(description.first()),
        getContent(cover.first())
    );
  }

  private String getContent(Element element) {
    return element == null ? "" : element.attr("content");
  }
}
