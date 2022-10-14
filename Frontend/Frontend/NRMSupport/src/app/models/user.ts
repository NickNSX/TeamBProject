export class User {

    constructor (
        public username?:string,
        public email?:string,
        public givenName?:string,
        public surname?:string,
        public userId?:string,
        public role?:string,
        public isActive?:boolean,
        public password?:string
    ) {}
}
