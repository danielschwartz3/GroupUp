import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import AccountCircle from '@material-ui/icons/AccountCircle';
import Logo from '../../Resources/Images/logo_transparent.png'
import './Navbar.css'

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

export default function ButtonAppBar(props) {
  const classes = useStyles();

  function editProfile() {
    props.setEditProfile(true)
    props.handleCreateModal()
  }

  return (
    <div className={classes.root}>
      <AppBar position="static" style={{ backgroundColor: '#133941' }}>
        <Toolbar className="navbar-container">
            <div className="navbar-btn-container">
              <img className="logo-style" src={Logo} />
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