import { Event } from "./event.model";

export interface Comment {
  id: number;
  description: string;
  nick: string;
  event: Event;

}

