class User {
    name;
    email;
    phone;

    constructor(name, email, phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    printInfo() {
        console.log("My name is " + this.name);
        console.log("My Email is " + this.email);
        console.log("My Phone is " + this.phone);
    }
}

var user = new User("Lee", "chrkb1569", "010-1234-5678");

user.printInfo();