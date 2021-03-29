import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import { unregisterCourseAction, getRegisteredAction, focusedConversationAction } from '../../redux';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { Button, Modal } from '@material-ui/core';
import Typography from '@material-ui/core/Typography';
import LikeButton from './LikeButton';
import TextField from '@material-ui/core/TextField';
import Select from '@material-ui/core/Select';
import Input from '@material-ui/core/Input';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import 'bootstrap/dist/css/bootstrap.min.css'
import StudentSearch from './StudentSearch';


const URL = 'http://localhost:8080'

const useStyles = makeStyles((theme) => ({
    table: {
      minWidth: 650
    },
    chatContainer: {
      height: '94vh',
      display: 'flex',
      flexDirection: 'column'
    },
    messageContainer: {
      width: '100%',
      display: 'flex',
      justifyContent: 'flex-end'
    },
    messageBox: {
      width: '90%'
    },
    sendBtn: {
      width: '10%'
    },
    paper: {
      position: 'absolute',
      width: 400,
      backgroundColor: theme.palette.background.paper,
      border: '2px solid #000',
      boxShadow: theme.shadows[5],
      padding: theme.spacing(2, 4, 3),
    },
    formControl: {
      margin: theme.spacing(1),
      minWidth: 120,
      maxWidth: 300,
    },
    modalSubContainer: {
      display: 'flex',
      alignItems: 'center',
    }
}));

function createData(id, name, members, messages) {
   if (name) {
      return { id, name, members, messages};
   }
   else {
      name = members[0];
      return { id, name, members, messages};
   }
}

function lastMessage(messages) {
   if (messages.length != 0) {
      return messages[0].message
   }
   else {
      return ""
   }
}

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`,
  };
}

function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};
 
 {/*going to need to do some work to get database data into this formate
 static only for visualize to be replaced with state variable conversations and data*/} 
const conversations = [ 
    createData(1,'428-Frontend', ["Daniel Schwartz", "Edem Nuviadenu", "Person 3", "Person 4", "Person 5"], 
      [{message: "how is everyone?", sender: "Edem Nuviadenu", timestamp: "2:45pm"},
      {message: "Lets work", sender: "Daniel Schwartz", timestamp: "7:45pm"}]),
    createData(2,'428', ["Daniel Schwartz", "Edem Nuviadenu",], 
      [{message: "I am scrum master, respect my authority", sender: "Daniel Schwartz", timestamp: "6:45am"},
      {message: "Lets work", sender: "Daniel Schwartz", timestamp: "7:45pm"}]),
    createData(3,'', ["Glen Xu"], 
      [{message: "Hey Glen", sender: "Ben Weiss", timestamp: "6:45am"},
      {message: "Hey Ben", sender: "Glen Xu", timestamp: "7:45pm"}])
];
var names = [];

const AllConversations = (props) => {
  const classes = useStyles();
  const theme = useTheme();
  const [modalStyle] = React.useState(getModalStyle);
  const [open, setOpen] = React.useState(false);
  const [personName, setPersonName] = React.useState([]);
  const [chatName, setChatName] = React.useState('');
  const [text, setText] = useState('');
  const [studentsList, setStudentsList] = useState([]);

  const { registeredCourses, email, name, userName } = props;

  useEffect(() => {
    getData();
    getAllStudentsName();
  }, [])

  const getData = async () => {
    const response = await axios.get(`${URL}/chats/${email}`);
  }

  const getAllStudentsName = async () => {
    const response = await axios.get(`${URL}/all/students/`);
    var nameList = []
    for(var i = 0; i < response.data.length; i++) {
      nameList.push(response.data[i].userName)
    }
    setStudentsList(nameList)
  }

  const handleChangeChatName = (event) => {
    setChatName(event.target.value);
    console.log(event)
  }
  
  const handleClose = () => {
    setOpen(false);
  };

  const startNewConversation = () => {
    setOpen(true);
  }

  const createConversation = async () => {
    if(personName.length > 10) {
      await axios.post(`${URL}/newchat/`, {
        "name" : chatName,
        "members" : [...studentsList, userName]
      })
      .then(res => {
        setStudentsList([])
        setChatName('')
      })
      .catch(error => {
        console.log(error)
      })
    }
  }

  const removeConversation = (id) => {
      console.log(id)
      /*props.unregisterCourseAction(id)*/
  }
  
  const focusedConvo = (id) => {
    props.focusedConversationAction(id);
  }

  function submitMessage(e) {
    e.preventDefault()

    conversations[0].messages.push(
      {
        message: text,
        sender: name,
        time: '12344'
      }
    )

    // console.log(conversations[0].messages)

    console.log(text)
    setText('')
  }

  const getConvo = (id) => {
      var convo;
      for (convo in conversations) {
         if (conversations[convo].id == id) {
            console.log(conversations[convo])
            return conversations[convo];
         }
      }
   }

    if (props.focusedConversation != -1) {
      return (
         <div className={classes.chatContainer}>
            <div>
              <TableContainer component={Paper}>
                <Table className={classes.table} size="small" aria-label="a dense table">
                    <TableHead>
                       <TableRow>
                          <TableCell align="left">{ getConvo(props.focusedConversation).name }</TableCell>
                          <TableCell align="right">
                            <Button className='button' color="primary" onClick={() => focusedConvo(-1)}>All Conversations</Button>
                          </TableCell>
                       </TableRow>
                    </TableHead>
                </Table>
              </TableContainer>
            </div>
            <div className="d-flex flex-column flex-grow-1">
              <div className="flex-grow-1 overflow-auto">
                <div className="d-flex flex-column align-items-start justify-content-end px-3">
                  {getConvo(props.focusedConversation).messages.map(({ message, sender, timestamp }) => (
                    <div 
                      key={timestamp}
                      className={`my-1 d-flex flex-column ${sender == name ? 'align-self-end' : ''}`} 
                    > {/* change id later! */}
                      <div 
                        className={`rounded px-2 py-1 ${sender == name ? 'bg-primary text-white' : 'border'}`}
                      >
                        {message} 
                        
                      </div>
                      <LikeButton/>
                      <div
                        className={`text-muted small ${sender == name ? 'text-right' : ''}`}
                      >
                        {sender == name ? 'You' : sender}
                      </div>
                      
                    </div>
                  ))}
                </div>
              </div>
            </div>
            <div className={classes.messageContainer}>
              <TextField
                className={classes.messageBox}
                id="message-container"
                value={text}
                onChange={e => setText(e.target.value)}
                multiline
                rows={2}
                placeholder="Enter Message..."
                variant="outlined"
              />
              <Button onClick={submitMessage} variant="outlined" color="primary" className='bg-primary text-white'>
                Send
              </Button>
            </div>
        </div>
        );
   }
    return (
        <div>
            <TableContainer component={Paper}>
                {conversations.length != 0 ? 
                    <Table className={classes.table} size="small" aria-label="a dense table">
                        <TableHead>
                        <TableRow>
                            <TableCell>Your Conversations</TableCell>
                            <TableCell align="right">Last Message</TableCell>
                            <TableCell align="right"></TableCell>
                            <TableCell align="right"></TableCell>
                            <TableCell align="right"></TableCell>
                            <TableCell align="right">Participants</TableCell>
                            <TableCell align="right">
                              <Button className='button' color='primary' onClick={() => startNewConversation()}>New Conversation</Button> {/*replace with image*/}
                           </TableCell> {/*replace with + image*/}
                        </TableRow>
                        </TableHead>
                            <TableBody>
                                {conversations && conversations.map(({ id, name, members, messages }) => (
                                    <TableRow key={id}>
                                       <TableCell component="th" scope="row">
                                       <Button className='button' color="primary" onClick={() => focusedConvo(id)}>{ name }</Button>
                                       </TableCell>
                                       <TableCell align="right">{ messages[0].message }</TableCell>
                                       <TableCell align="right">From: { messages[0].sender }; { messages[0].timestamp }</TableCell>
                                       <TableCell align="right"> </TableCell>
                                       <TableCell align="right"></TableCell>
                                       <TableCell align="right">{ members.length }</TableCell>
                                       <TableCell align="right">
                                           <Button className='button' color="secondary" onClick={() => removeConversation(id)}>Delete Conversation</Button>
                                       </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody> 
                    </Table> :
                    <div>
                        <Button className='button'>New Message</Button>
                        <h4>
                            You currently have no conversations, click new message to start chatting!
                        </h4>
                    </div>
                }
            </TableContainer>
            <Modal
              open={open}
              onClose={handleClose}
              aria-labelledby="simple-modal-title"
              aria-describedby="simple-modal-description"
            >
              <div style={modalStyle} className={classes.paper}>
                <h2 id="simple-modal-title">New Chat</h2>
                <div className={classes.modalSubContainer}>
                  <Typography id="simple-modal-description">Chat Name:</Typography>
                  <TextField 
                    id="standard-basic"
                    value={chatName}
                    onChange={handleChangeChatName}
                  />
                </div>
                <div className={classes.modalSubContainer}>
                  <Typography id="simple-modal-description">Members:</Typography>
                  <FormControl className={classes.formControl}>
                    <StudentSearch studentsList = {studentsList}/>
                  </FormControl>

                 
                </div>
                <Button className='button' color="primary" onClick={createConversation}>Create</Button>
              </div>
            </Modal>
        </div>
    );
}

const mapStateToProps = (state) => ({
  registeredCourses: state.registeredCourses,
  email: state.user.email,
  name: state.user.name,
  userName: state.user.userName,
  conversations: state.conversations,
  focusedConversation: state.focusedConversation
}); 

export default connect(
    mapStateToProps,
    { unregisterCourseAction, getRegisteredAction, focusedConversationAction }
)(AllConversations);