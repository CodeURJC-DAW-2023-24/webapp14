export interface Event{
  id: number;
  title: string;
  description: string;
  place: string;
  date: string;
  duration: string;
  category:string;
  n_tickets:number;
  comments: Comment[];
  imageUrl: string;
}
