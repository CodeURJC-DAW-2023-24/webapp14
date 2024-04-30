export interface User {
  id: number;
  nick: string;
  name: string;
  surname: string;
  email: string;
  encodedPassword: string;
  roles: string[];
  studyCenter: string;
  phone: number;
  editor: boolean;
  comments: Comment[];
  events: Event[];
}
