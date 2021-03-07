import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import { unregisterCourseAction, getRegisteredAction, focusedConversationAction } from '../../redux';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { Button, Modal } from '@material-ui/core';
import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import 'bootstrap/dist/css/bootstrap.min.css'

const URL = 'http://localhost:8080'

const useStyles = makeStyles({
    table: {
      minWidth: 650
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
    }
});

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

const AllConversations = (props) => {
    const classes = useStyles();
    const [modalOpen, setModalOpen] = useState(false);
    const [text, setText] = useState('');

    const { registeredCourses, email, name } = props;

    useEffect(() => {
      getData();
    })

    const getData = async () => {
      const response = await axios.get(`${URL}/chats/${email}`);
    }

    function closeModal() {
      setModalOpen(false);
    }

    const startNewConversation = () => {
      console.log(true)
      setModalOpen(true);
      console.log(name)
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
         <div>
            {/* <TableContainer component={Paper}>
                    <Table className={classes.table} size="small" aria-label="a dense table">
                        <TableHead>
                           <TableRow>
                              <TableCell alight="left">Conversations</TableCell>
                              <TableCell align="left">{ getConvo(props.focusedConversation).name }</TableCell>
                              <TableCell align="right">
                                <Button className='button' color="primary" onClick={() => focusedConvo(-1)}>All Conversations</Button>
                              </TableCell>
                           </TableRow>
                        </TableHead>
                        <TableBody>
                           <TableRow>
                              <TableCell component="th" scope="row">
                              <List style={{width: "30%"}}>
                              {conversations && conversations.map(({ id, name, members, messages }) => (
                                //  <ListItem button onClick={() => focusedConvo(id)}>
                                 <ListItem>
                                    <ListItemText
                                      primary={name}
                                      secondary={
                                        <React.Fragment>
                                          <Typography
                                            component="span"
                                            variant="body2"
                                            className={classes.inline}
                                            color="textPrimary"
                                          >
                                            {messages[0].sender} 
                                          </Typography>
                                          : { messages[0].message } - { messages[0].timestamp }
                                        </React.Fragment>
                                      }
                                    />
                                 </ListItem>
                              ))}
                              </List>
                              </TableCell>
                              <TableCell>
                              <List>
                                 {getConvo(props.focusedConversation).messages.map(({ message, sender, timestamp }) => (
                                       <ListItem>
                                            <ListItemText
                                              primary={message}
                                              secondary={
                                                <React.Fragment>
                                                  <Typography
                                                    component="span"
                                                    variant="body2"
                                                    className={classes.inline}
                                                    color="textPrimary"
                                                  >

                                                  </Typography>
                                                  { sender } - { timestamp }
                                                </React.Fragment>
                                              }
                                            />
                                 </ListItem>
                                 ))}
                              </List>
                              </TableCell>
                              <TableCell></TableCell>
                           </TableRow>
                        </TableBody> 
                    </Table> 
            </TableContainer> */}
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

            {/* <Modal show={modalOpen} onHide={closeModal}>
                <div>
                </div>
            </Modal> */}
        </div>
    );
}

const mapStateToProps = (state) => ({
  registeredCourses: state.registeredCourses,
  email: state.user.email,
  name: state.user.name,
  conversations: state.conversations,
  focusedConversation: state.focusedConversation
}); 

export default connect(
    mapStateToProps,
    { unregisterCourseAction, getRegisteredAction, focusedConversationAction }
)(AllConversations);