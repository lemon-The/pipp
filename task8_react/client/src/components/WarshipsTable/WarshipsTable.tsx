import { Warship } from '../../shared/types/types.ts';
import { FC } from 'react';
import { IconButton, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import DeleteSharpIcon from '@mui/icons-material/DeleteSharp';

interface WarshipsTableProps {
  warships: Warship[];
}

const WarshipsTable: FC<WarshipsTableProps> = ({ warships }) => {
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell align="right">Class</TableCell>
            <TableCell align="right">Commission Date</TableCell>
            <TableCell align="right">Decommission Date</TableCell>
            <TableCell align="right">Country</TableCell>
            <TableCell>Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {warships.map((w) => (
            <TableRow key={w.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
              <TableCell component="th" scope="row">
                {w.name}
              </TableCell>
              <TableCell align="right">{w.shipClass}</TableCell>
              <TableCell align="right">{w.commissionDate}</TableCell>
              <TableCell align="right">{w.decommissionDate}</TableCell>
              <TableCell align="right">{w.country.name}</TableCell>
              <TableCell>
                <IconButton aria-label="close" onClick={() => {}}>
                  <DeleteSharpIcon color="error" />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default WarshipsTable;
