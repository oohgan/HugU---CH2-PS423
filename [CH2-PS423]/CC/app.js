require('dotenv').config()
const express = require('express')
const mongoose = require('mongoose')
const passport = require('passport');
const bodyParser = require('body-parser');
const UserModel = require('./models/userModel');
const usersRouter = require('./routes/users')
const servicesRouter = require('./routes/services')
require('./auth/auth')

const app = express()

mongoose.connect('mongodb+srv://yuansheva:%40Sheva210@bangkit.uykenqg.mongodb.net/', { 
    useNewUrlParser: true 
})
const db = mongoose.connection
db.on('error', (error) => console.error(error))
db.once('open', () => console.log('Connected to Database'))

app.use(express.json())
app.use(bodyParser.urlencoded({ extended: false }));

app.use("/", express.static("public"));
app.use("/", usersRouter);
app.use('/user', passport.authenticate('jwt', { session: false }), servicesRouter);

app.use((req, res, next) => {
  res.status(404);
  res.json({ message: "Page not found" });
});


app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.json({ error: err });
});

const port = process.env.PORT || 8080; 
app.listen(port, () => { 
    console.log('Hello listening port', port); });
