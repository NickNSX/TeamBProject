export class CreateReimb {

    constructor(
        public userId:string,
        public amount:number,
        public description:string,
        public type:string
    ) {} 
}
