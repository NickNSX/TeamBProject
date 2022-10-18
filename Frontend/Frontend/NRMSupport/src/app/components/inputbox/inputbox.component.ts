import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-inputbox',
  templateUrl: './inputbox.component.html',
  styleUrls: ['./inputbox.component.css']
})
export class InputboxComponent implements OnInit {

  todo1 = {
   
        Amount: "1000",
        Submitted: "10:12",
        Resolved: "9:12",
        Description: "This is first Reim",
        AuthorID: "Max",
        ResolverID: "Resolver's name",
        StatusID: "Pending",
        TypeID: "Travel"
  }

  todo2 = {
        Amount: "1",
        Submitted: "1:23",
        Resolved: "1:24",
        Description: "This is Second Reim",
        AuthorID: "Max",
        ResolverID: "Resolver's name",
        StatusID: "Pending",
        TypeID: "Food"
  }

  remiArray = [this.todo1, this.todo2]


  constructor() { }
  hiddenGrid: boolean = true;
  ngOnInit(): void {
    this.hiddenGrid = true;
  }
  hiddenStart: boolean = false;
  

  showReim()
  {
    this.hiddenStart = !this.hiddenStart 
    this.hiddenGrid = !this.hiddenGrid
    console.log(this.hiddenStart)
    console.log(this.hiddenGrid)
  }
}