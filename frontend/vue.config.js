module.exports = {
<<<<<<< HEAD

};
=======
  "devServer": {
    "proxy": {
      "/api": {
        "target": "http://localhost:8098",
        "ws": true,
        "changeOrigin": true
      }
    }
  },
  "outputDir": "target/dist",
  "assetsDir": "static",
  "transpileDependencies": [
    "vuetify"
  ]
}
>>>>>>> f4fafcee461a40a43b8abf812dab4b91196cd38c
