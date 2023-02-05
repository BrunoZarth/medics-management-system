
const proxy = [
    {
        context: "/api",
        //target: "http://localhost:8080",
        target: "http://backend:8080",
        pathRewrite: {"^/api": ""}
    }
];
module.exports = proxy;
