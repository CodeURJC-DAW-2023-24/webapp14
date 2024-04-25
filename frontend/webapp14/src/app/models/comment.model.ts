export class Comment {
  id: number;
  description: string;
  nick: string;
  event: Event;

  constructor(id: number, description: string, nick: string, event: Event) {
    this.id = id;
    this.description = description;
    this.nick = nick;
    this.event = event;
  }
}
