import { Component, OnInit } from '@angular/core';
import { Reimbursements } from 'src/app/models/reimbursements';
import { UserService } from 'src/app/services/user.service';
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
    id: undefined,
    amount: undefined,
    submitted: undefined,
    resolved: undefined,
    description: undefined,
    authorId: undefined,
    resolverId: undefined,
    status: undefined,
    type: undefined
  };

  constructor(private rs: ReimbursementServiceTsService, private us: UserService) { }
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
  

    if(this.remiArray.length > 1)
    {
      for( let i =this.remiArray.length;i >=0 ;i-- ){
        this.remiArray.pop()
    }
  }
    // this.rs.getReimbursement().subscribe(
    //   (data:any) => {console.log(data)
    //     for(let i = 0; i<data.length; i++)
    //     {
    //       this.remiArray.push(data[i]);
    //     }
      
    //   }
    // )

    
  }

  getAllReim(){

    if(this.remiArray.length > 0)
    {
      for( let i =this.remiArray.length;i >=0 ;i-- ){
        this.remiArray.pop()
      }
  }
  if(this.us.user.role == "Finance Manager")
  {
    this.rs.getReimbursement("/manager").subscribe(
      (data:any) => {console.log(data)
        for(let i = 0; i<data.length; i++)
        {
          this.remiArray.push(data[i]);
        }
      
      }
    )
  }else if(this.us.user.role != "Finance Manager" && this.us.user.role != undefined)
  {
    this.rs.getReimbursement("/employee/"+this.us.user.userId).subscribe((data:any) => {console.log(data)
      for(let i = 0; i<data.length; i++)
      {
        this.remiArray.push(data[i]);
      }
    
    })
  }

  }





}
