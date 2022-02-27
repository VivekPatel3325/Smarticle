export const apiUrl =  process.env.NODE_ENV === "development"
  ? "http://localhost:3000/api" // development api
  : "http://localhost:3000/api"; // production api
export const serverUrl = process.env.NODE_ENV === "development"
? "http://localhost:8080/smarticleapi" // development api
: "http://localhost:8080/smarticleapi"; // production api