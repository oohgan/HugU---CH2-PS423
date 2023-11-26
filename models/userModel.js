const mongoose = require('mongoose')

const userSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  email: {
    type: String,
    reqired: true,
    unique: true,
  },
  password: {
    type: String,
    reqired: true
  },
  age: {
    type: Number,
    default: 0,
  },
  gender: {
    type: String,
    required: true
  },
  occupation: {
    type: String,
    required: true
  },
  userDate: {
    type: Date,
    required: true,
    default: Date.now
  }
})

const userModel = mongoose.model("user", userSchema);

module.exports = userModel;