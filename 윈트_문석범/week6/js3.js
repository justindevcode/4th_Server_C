const num = prompt("Enter a number");
try {
  const result = num / 0;
  console.log("Result: " + result);
} catch (error) {
  console.log("Cannot divide by zero");
}