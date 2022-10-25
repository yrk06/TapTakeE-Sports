const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
app.use(
        '/api',
        createProxyMiddleware({
            target: 'http://localhost:8090',
            changeOrigin: true,
        })

    );
    app.use(
        '/login',
        createProxyMiddleware((req) => req.method === 'POST', {
            target: 'http://localhost:8090',
            changeOrigin: true,
        })

    );
    app.use(
        '/logout',
        createProxyMiddleware({
            target: 'http://localhost:8090',
            changeOrigin: true,
        })

    );
};