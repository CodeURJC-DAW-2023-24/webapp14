export interface User {
  id: number;
  NICK: string;
  name: string;
  surname: string;
  email: string;
  password: string;
  roles: string[];
  studyCenter: string;
  phone: number;
  editor: boolean;
  comments: Comment[];
  events: Event[];

}
