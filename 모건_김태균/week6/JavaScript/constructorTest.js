class User {
  constructor(name, email, phone) {
    this.name = name;
    this.email = email;
    this.phone = phone;
  }
  printInfo() {
    console.log("이름: " + this.name);
    console.log("메일: " + this.email);
    console.log("번호: " + this.phone);
  }
}

const user = new User("모건", "taegyun7553@naver.com", "010-1234-5678");
user.printInfo();
