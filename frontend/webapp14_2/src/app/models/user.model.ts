import { Event } from "./event.model";

export interface User {
  id: number;
  name: string;
  surname: string;
  email: string;
  encodedPassword: string;
  roles: string[];
  studyCenter: string;
  phone: number;
  editor: boolean;
  events: Event[];
  NICK: string;


}
