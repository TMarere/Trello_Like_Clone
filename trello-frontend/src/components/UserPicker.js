////Notice: use material ui multiple seletor as the template 

import React, {useEffect, useState}from "react";
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Chip from '@mui/material/Chip';

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

function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName !== name
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

export default function UserPicker({onUserSelect}) {
  const theme = useTheme();
  const [personName, setPersonName] = useState('');
  const [users, setUsers] = React.useState([]);

  const handleChange = (event) => {
    setPersonName(event.target.value);
    onUserSelect(users.find(user => user.name === event.target.value));
  };

  React.useEffect(() => {
    fetch("http://localhost:8070/user/getAll/")
      .then(response => response.json())
      .then(data => setUsers(data))
      .catch(error => console.error(error));
  }, []);

  return (
    <div>
      <FormControl sx={{ m: 1, width: 300 }}>
        <InputLabel id="demo-multiple-chip-label">Assign User</InputLabel>
        <Select
          labelId="demo-multiple-chip-label"
          id="demo-multiple-chip"
          value={personName}
          onChange={handleChange}
          input={<OutlinedInput id="select-multiple-chip" label="Chip" />}
          renderValue={(selected) => (
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
              <Chip label={selected} />
            </Box>
          )}
          MenuProps={MenuProps}
        >
          {users.map((user) => (
            <MenuItem
              key={user.id}
              value={user.name}
              style={getStyles(user.name, personName, theme)}
            >
              {user.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
}
