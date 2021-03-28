import React, { useEffect, useState } from "react";
import Chip from "@material-ui/core/Chip";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";

const useStyles = makeStyles((theme) => ({
  root: {
    width: 280,
    "& > * + *": {
      marginTop: theme.spacing(2),
    },
  },
}));

export default function StudentSearch(props) {
  const studentsList = props.studentsList;

  const classes = useStyles();
  return (
    <div className={classes.root}>
      <Autocomplete
        multiple
        id="tags-standard"
        options={studentsList}
        getOptionLabel={(option) => option.name}
        renderInput={(params) => (
          <TextField
            {...params}
            variant="standard"
            placeholder="search student name"
          />
        )}
      />{" "}
    </div>
  );
}
