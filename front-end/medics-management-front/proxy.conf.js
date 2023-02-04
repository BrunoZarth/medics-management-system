
const proxy = [
    {
        context: "/api",
        target: "http://backend:8080",
        pathRewrite: {"^/api": ""}
    }
];
module.exports = proxy;
