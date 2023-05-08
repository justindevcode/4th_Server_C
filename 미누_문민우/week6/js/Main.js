class User {
    constructor(name, email, phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    printInfo(){
        let str;
        str = "이름: " + this.name + '\n'
            + "이메일: " + this.email + '\n'
            + "휴대폰번호: " + this.phone;

        console.log(str);
    }
}

const strArray = ['Hello', 'Hi', 'Bye'];

for(let i = 0; i < strArray.length; i++) {
    console.log(toUpperCase(strArray[i]));
}
function toUpperCase(str) {
    str = str.toUpperCase();
    return str;
}

console.log("=====================================")

const user = new User("미누", "test1234@gmail.com", "010-1234-5678");
user.printInfo();

console.log("=====================================")

const readline = require("readline");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

rl.on("line", (line) => {
    try {
        const num = 0;
        if(num === 0) {
            throw new Error("Cannot divide by zero");
        } else {
            console.log(line / num);
        }

    } catch (e) {
        console.log(e.message);
    }

    rl.close();
});

rl.on('close', () => {
    process.exit();
})