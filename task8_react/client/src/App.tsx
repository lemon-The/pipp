import { useCallback, useEffect, useState } from 'react';

import './App.css';
import AddModal from './components/AddModal/AddModal.tsx';
import WarshipsTable from './components/WarshipsTable/WarshipsTable.tsx';
import { Box, Button, styled } from '@mui/material';
import { Warship } from './shared/types/types.ts';
import { getWarships } from './shared/api/fetchers/warshipsFetcher.ts';

function App() {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [warships, setWarships] = useState<Warship[]>([]);

  const fetchWarships = useCallback(async () => {
    try {
      const response = await getWarships();
      setWarships(response.data);
    } catch {
      console.error('Error on fetch');
    }
  }, []);

  useEffect(() => {
    void fetchWarships();
  }, []);

  return (
    <StyledWrapper>
      <AddModal open={isOpen} onClose={() => setIsOpen(false)} setWarships={setWarships} />
      <Button onClick={() => setIsOpen(true)} variant="contained" sx={{ width: '40%' }}>
        + Add Warship
      </Button>
      <WarshipsTable warships={warships} />
    </StyledWrapper>
  );
}

export default App;

const StyledWrapper = styled(Box)`
  display: flex;
  flex-direction: column;
  gap: 16px;
`;
