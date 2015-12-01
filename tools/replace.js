"use strict";

let fs = require("fs");

if (process.argv.length != 4) {
  console.log("Usage: iojs this_script.js input output");
  process.exit(0);
}

let lines = fs.readFileSync(process.argv[2], "utf8").split('\n');

if (lines.length <= 2) {
  console.log("Too few lines.");
  process.exit(0);
}

let pattern = new RegExp(lines[0])
  , replica = lines[1]
  , result = ""
  ;

for (let i = 2; i < lines.length; i++) {
  result += lines[i].replace(pattern, replica);
  result += '\n';
}

fs.writeFileSync(process.argv[3], result);


