export interface Event{
  id: number;
  title: string;
  description: string;
  place: string;
  date: string;
  duration: string;
  comments: Comment[];
}
