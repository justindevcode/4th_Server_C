var input = prompt("값을 입력해주세요");

try {
    var divisionNumber = 0;
    if(divisionNumber == 0) throw new Error();
    result = input / divisionNumber;
    console.log("Division Result is " + result);
} catch(e) {
    console.log("Cannot divide by zero");
}