import { Event } from "../models/event.model";

export interface Comment {
  id: number;
  description: string;
  nick: string;
  event: Event;

}

