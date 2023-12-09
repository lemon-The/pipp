import { Box, Dialog, FormHelperText, styled, TextField } from '@mui/material';

export const StyledModal = styled(Dialog)`
  .MuiBackdrop-root {
    backdrop-filter: blur(3px);
  }
`;

export const StyledModalContainer = styled(Box)`
  width: 450px;
  height: 500px;
`;

export const StyledInput = styled(TextField)`
  width: 100%;
`;

export const StyledTextHelper = styled(FormHelperText)`
  visibility: visible;
  position: absolute;
  margin-bottom: 8px;
`;

export const StyledFormWrapper = styled('form')`
  display: flex;
  flex-direction: column;
  padding: 8px 16px;
  gap: 24px;
`;
