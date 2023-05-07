function toUpperCase(stringValue) {
    return stringValue.toString().toUpperCase();
}

const strArr = ["Level", "hello", "for", "test", "move"];

for(var value of strArr) {
    console.log(toUpperCase(value));
}