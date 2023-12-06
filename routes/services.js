const express = require('express')
const router = express.Router()
const userModel = require('../models/userModel')

router.get('/profile', async (req, res, next) => {
  const users = await userModel.find({ email: req.user.email })
  res.status(200);
  res.json(users[0]);
  }
);

router.get('/chat-bot', async (req, res, next) => {
  res.status(200);
  res.json({ status: "this chat-bot api " });
  }
);

router.get('/mental-health', async (req, res, next) => {
  res.status(200);
  res.json({ status: "this mental-health api " });
  }
);

router.get('/feedback', async (req, res, next) => {
  res.status(200);
  res.json({ status: "this feedback api " });
  }
);


module.exports = router