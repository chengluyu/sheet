"use strict";

let fs = require("fs")
  , colors = require("colors")
  , path = require("path")
  ;

let counter = 0, nonempty = 0;

function countLine(filePath) {
  let lines = fs.readFileSync(filePath, "utf8").split('\n');
  let c = 0;
  lines.forEach(function (line) {
    if (line == false) c++;
  });
  console.log(filePath.green, `: ${lines.length} lines, ${c} empty lines.`);
  counter += lines.length;
  nonempty += c;
}

function traverse(parent) {
  let dirs = fs.readdirSync(parent);

  dirs.forEach(function (fileName) {
    if (fileName.charAt(0) === '.')
      return;
    if (fileName === "node_modules")
      return;

    let stat = fs.statSync(path.join(parent, fileName));

    if (stat.isDirectory()) {
      traverse(path.join(parent, fileName));
    } else if (stat.isFile() && path.extname(fileName) === ".java") {
      countLine(path.join(parent, fileName));
    }
  })
}

if (process.argv.length <= 2)
  console.log("Usage: iojs countLine.js DIRECTORY");
else {
  traverse(process.argv[2]);
  console.log(`Total: ${counter} lines, ${counter - nonempty} non-empty lines.`);
}