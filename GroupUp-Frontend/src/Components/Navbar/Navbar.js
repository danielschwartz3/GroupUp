import React from 'react';
import axios from 'axios'
import { connect } from 'react-redux';
import { intializeUserAction } from '../../redux';
import Cookies from 'js-cookie'
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import AccountCircle from '@material-ui/icons/AccountCircle';
import Logo from '../../Resources/Images/logo_transparent.png'
import './Navbar.css'

const URL = 'http://localhost:8080'

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    color: '#FFFFFF'
  },
}));

 function ButtonAppBar(props) {
  const classes = useStyles();

  const editProfile = async () => {
    props.setEditProfile(true)
    props.handleCreateModal()
    if (Cookies.get("GroupUpUserEmailCookie") != 'undefined') {
      const response = await axios.get(URL + '/student/' + `${Cookies.get("GroupUpUserEmailCookie")}`);
      var user = {
        userRole: response.data,
        userName: response.data.userName,
        name: response.data.name,
        email: props.email,
        userInstitution: response.data.institution}
      
      props.intializeUserAction(user)
      console.log(response)
      // props.setName(response.data.name)
      // props.setEmail(props.email)
      // props.setInstitution(response.data.institution)
      // props.setUserName(response.data.userName)
      console.log(Cookies.get("GroupUpUserEmailCookie"))
    }
  }

  return (
    <div className={classes.root}>
      <AppBar position="static" style={{ backgroundColor: '#133941' }}>
        <Toolbar className="navbar-container">
            <div className="navbar-btn-container">
              <img className="logo-style" src={Logo} />
                <Button>
                    <Typography variant="h6" className={classes.title}>
                        Messages
                    </Typography>
                </Button>
                <Button> 
                    <Typography variant="h6" className={classes.title}>
                        My Courses
                    </Typography>
                </Button>
                <Button>
                    <Typography variant="h6" className={classes.title}>
                        All Courses
                    </Typography>
                </Button>
            </div>
            <IconButton
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                color="inherit"
                onClick={editProfile}
            >
                <AccountCircle />
            </IconButton>
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default connect(
  null,
  { intializeUserAction }
)(ButtonAppBar);