export class MessageDTO {
  id = "";
  timestamp = 0;
  from = "";
  to = "";
  type = "";
  body = "";
  reaction = "";
  showDelete? = false;
  showEmojis? = false;
  selectedEmoji?: string;
}
