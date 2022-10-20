export class UpdateReimb {

    constructor(
        public reimbId:string,
        public amount?:number,
        public description?:string,
        public type?:string,
        public resolverId?:string,
        public status?:string
        ) {}
}
