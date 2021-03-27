import React from 'react';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Favorite from '@material-ui/icons/Favorite';
import FavoriteBorder from '@material-ui/icons/FavoriteBorder';
  
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
      const text = this.state.liked ? 'liked' : 'haven\'t liked';
      const label = this.state.liked ? 'Unlike' : 'Like'
      return (
        <div className="customContainer">
          <button className="btn btn-primary" onClick={this.handleClick}>
            {label}</button>
          {/* <p>
            you {text} this. Click to toggle.
          </p> */}
        </div>
        
      );
    }
  }
  

  export default LikeButton;
  

  