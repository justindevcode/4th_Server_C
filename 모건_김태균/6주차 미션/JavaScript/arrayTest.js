const toUpperCase = (a) => {
  const newArray = [];
  for (let i = 0; i < a.length; i++) {
    newArray.push(a[i].toUpperCase());
  }
  return newArray;
};

const a = ["morgan", "taegyun", "ellen"];
const temp = toUpperCase(a);
console.log(temp);
