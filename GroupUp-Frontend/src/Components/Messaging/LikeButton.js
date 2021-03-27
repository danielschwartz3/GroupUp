import React, { useState } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import Alert from '@material-ui/lab/Alert';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { intializeUserAction, initializeUserCoursesAction } from '../../redux';
import Cookies from 'js-cookie'

const URL = 'http://localhost:8080'


  const LikeButton = () => {
    if (this.state.liked) {
      axios.post(`${URL}/message/react/`, null, {
          params: {
              email: props.email,
              reaction: props.reaction,
              messageID: props.messageID,
          }
      }).then(function (response) {
        })
        .catch(function (error) {
          console.log(error);
        });
    } else {
      axios.post(`${URL}/message/unreact/`, null, {
        params: {
            email: props.email,
            reaction: props.reaction,
            messageID: props.messageID,
        }
    }).then(function (response) {
      })
      .catch(function (error) {
        console.log(error);
      });
    }
  }

  
class LikeButton extends React.Component {
    constructor() {
      super();
      this.state = {
        liked: false
      };
      this.handleClick = this.handleClick.bind(this);
    } 
   
    handleClick() {
      this.setState({
        liked: !this.state.liked
      });
      //if liked = false, rest call
      }
    
    render() {
      const label = this.state.liked ? 'Unlike' : 'Like'
      return (
        <div className="customContainer">
          <button className="btn btn-primary" onClick={this.handleClick}>
            {label}</button>
        </div>
        
      );
    }
  }
  

  export default LikeButton;
  

  