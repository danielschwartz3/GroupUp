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
    if (Cookies.get("GroupUpUserEmailCookie") != 'undefined') {
      const response = await axios.get(URL + '/student/' + `${Cookies.get("GroupUpUserEmailCookie")}`);
      props.setUserName(response.data.userName);
      props.setName(response.data.name);
      props.setEmail(Cookies.get("GroupUpUserEmailCookie"));
      props.setInstitution(response.data.institution);
      //props.intializeUserAction(user)
      // props.setName(response.data.name)
      // props.setEmail(props.email)
      // props.setInstitution(response.data.institution)
      // props.setUserName(response.data.userName)
      console.log(Cookies.get("GroupUpUserEmailCookie"))
    }
    props.handleCreateModal();
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

const mapStateToProps = (state) => ({
  user: state.user
}); 


export default connect(
  mapStateToProps,
  { intializeUserAction }
)(ButtonAppBar);