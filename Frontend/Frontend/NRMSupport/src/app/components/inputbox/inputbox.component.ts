import { Component, OnInit } from '@angular/core';
import { Reimbursements } from 'src/app/models/reimbursements';
import {ReimbursementServiceTsService} from '../../services/reimbursement.service.ts.service';

@Component({
  selector: 'app-inputbox',
  templateUrl: './inputbox.component.html',
  styleUrls: ['./inputbox.component.css']
})
export class InputboxComponent implements OnInit {

  
  authorID:string = "";
  remiArray:any = [ ];

  ReimSingle:Reimbursements = {
    reimbId: undefined,
    amount: undefined,
    submitted: undefined,
    resolved: undefined,
    description: undefined,
    authorId: undefined,
    resolverId: undefined,
    statusId: undefined,
    typeId: undefined
  };

  constructor(private rs: ReimbursementServiceTsService) { }
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

  callingServiceForRei(){
  
    // this.rs.getRemb(this.authorID).subscribe(
    //   (data:any) => {console.log(data)
    //     for(let i = 0; i<data.length; i++)
    //     {
    //       this.remiArray.push(data[i]);
    //     }
      
    //   }
    // )

    
  }

}


