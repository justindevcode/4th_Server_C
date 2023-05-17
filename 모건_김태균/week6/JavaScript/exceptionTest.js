const n = prompt("입력해주세요");
try {
  const result = int(n / 0);
  console.log(result);
} catch (e) {
  console.log("Cannot divide by zero");
}
