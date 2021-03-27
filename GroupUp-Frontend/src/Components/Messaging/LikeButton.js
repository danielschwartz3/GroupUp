import React, { useState } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import Alert from '@material-ui/lab/Alert';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { intializeUserAction, initializeUserCoursesAction } from '../../redux';
import Cookies from 'js-cookie'

const URL = 'http://localhost:8080'


  // const Like = () => {
  //   if (this.state.liked) {
  //     axios.post(`${URL}/message/react/`, null, {
  //         params: {
  //             email: props.email,
  //             reaction: props.reaction,
  //             messageID: props.messageID,
  //         }
  //     }).then(function (response) {

  //       axios.get(`${URL}/message/reactors/`, null, {
  //         params: {
  //             messageID: props.messageID,
  //         }
  //         }).then(function (response) {
  //         //store all reactors
  //           })
  //       })
  //       .catch(function (error) {
  //         console.log(error);
  //       });
  //   } else {
  //     axios.post(`${URL}/message/unreact/`, null, {
  //       params: {
  //           email: props.email,
  //           reaction: props.reaction,
  //           messageID: props.messageID,
  //       }
  //           }).then(function (response) {
  //            axios.get(`${URL}/message/reactors/`, null, {
  //              params: {
  //               messageID: props.messageID,
  //             }
  //              }).then(function (response) {
  //              //store all reactors
  //              })

  //     })
  //     .catch(function (error) {
  //       console.log(error);
  //     });
  //   }
  // }

  
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
    
      }
    
    render() {
      const label = this.state.liked ? 'Unlike' : 'Like'
      return (
        <div className="customContainer">
          <div class="btn-group">
              <button type="button" class="btn btn-primary"onClick={this.handleClick}>{label}</button>
              <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
              <span class="sr-only">Toggle Dropdown</span>
              </button>
              <div class="dropdown-menu">
              </div>
          </div>
        </div>
        
      );
    }
  }
  

  export default LikeButton;
  

  