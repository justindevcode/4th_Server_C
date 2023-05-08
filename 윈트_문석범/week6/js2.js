class User {
  constructor(name, email, phone) {
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  printInfo() {
    console.log("Name: " + this.name);
    console.log("Email: " + this.email);
    console.log("Phone: " + this.phone);
  }
}

const user = new User("홍길동", "hong@example.com", "010-1234-5678");
user.printInfo();