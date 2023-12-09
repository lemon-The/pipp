import { Box, Button, Divider, IconButton, Typography } from '@mui/material';
import { Dispatch, FC, SetStateAction } from 'react';
import {
  StyledFormWrapper,
  StyledInput,
  StyledModal,
  StyledModalContainer,
  StyledTextHelper,
} from './AddModal.styled.ts';
import CloseIcon from '@mui/icons-material/Close';
import { Controller, SubmitHandler, useForm } from 'react-hook-form';
import { createWarship } from '../../shared/api/fetchers/warshipsFetcher.ts';
import { Warship } from '../../shared/types/types.ts';

interface ModalProps {
  open: boolean;
  onClose: () => void;
  setWarships: Dispatch<SetStateAction<Warship[]>>;
}

interface ModalInputProps {
  name: string;
  shipClass: string;
  commissionDate: string;
  decommissionDate: string;
}

const AddModal: FC<ModalProps> = ({ open, onClose, setWarships }) => {
  const { control, formState, handleSubmit, reset, clearErrors } = useForm<ModalInputProps>();

  const handleClose = () => {
    onClose();
    reset();
    clearErrors();
  };

  const onSubmit: SubmitHandler<ModalInputProps> = async (data) => {
    try {
      const response = await createWarship(data);
      setWarships((prev) => [...prev, response.data]);
    } catch {
      console.error('Error in fetch');
    }
  };

  return (
    <StyledModal open={open} onClose={onClose}>
      <StyledModalContainer>
        <Box
          sx={{
            padding: '8px 16px',
          }}
        >
          <Box>
            <Typography fontSize="24px">Title</Typography>
            <IconButton
              aria-label="close"
              onClick={handleClose}
              sx={{
                position: 'absolute',
                right: 8,
                top: 8,
                color: 'gray',
              }}
            >
              <CloseIcon />
            </IconButton>
          </Box>
        </Box>
        <Divider />
        <StyledFormWrapper onSubmit={handleSubmit(onSubmit)}>
          <Controller
            name="name"
            control={control}
            defaultValue=""
            rules={{ required: true }}
            render={({ field }) => (
              <Box>
                <StyledInput
                  {...field}
                  variant="outlined"
                  id="name"
                  label="Название"
                  autoFocus
                  error={!!formState.errors.name}
                />
                {formState.errors.name && (
                  <StyledTextHelper error>Это поле обязательно для заполнения</StyledTextHelper>
                )}
              </Box>
            )}
          />
          <Controller
            name="shipClass"
            control={control}
            defaultValue=""
            rules={{ required: true }}
            render={({ field }) => (
              <Box>
                <StyledInput
                  {...field}
                  variant="outlined"
                  id="shipClass"
                  label="Название класса"
                  autoFocus
                  error={!!formState.errors.name}
                />
                {formState.errors.name && (
                  <StyledTextHelper error>Это поле обязательно для заполнения</StyledTextHelper>
                )}
              </Box>
            )}
          />
          <Controller
            name="commissionDate"
            control={control}
            defaultValue=""
            rules={{ required: true }}
            render={({ field }) => (
              <Box>
                <StyledInput
                  {...field}
                  variant="outlined"
                  id="commissionDate"
                  label="Дата"
                  autoFocus
                  error={!!formState.errors.name}
                />
                {formState.errors.name && (
                  <StyledTextHelper error>Это поле обязательно для заполнения</StyledTextHelper>
                )}
              </Box>
            )}
          />
          <Controller
            name="decommissionDate"
            control={control}
            defaultValue=""
            rules={{ required: true }}
            render={({ field }) => (
              <Box>
                <StyledInput
                  {...field}
                  variant="outlined"
                  id="decommissionDate"
                  label="Дата"
                  autoFocus
                  error={!!formState.errors.name}
                />
                {formState.errors.name && (
                  <StyledTextHelper error>Это поле обязательно для заполнения</StyledTextHelper>
                )}
              </Box>
            )}
          />
          <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
            <Button variant="contained" color="primary" onClick={handleClose} sx={{ bgcolor: 'gray', width: '45%' }}>
              Отмена
            </Button>
            <Button type="submit" variant="contained" color="primary" sx={{ width: '45%' }}>
              Сохранить
            </Button>
          </Box>
        </StyledFormWrapper>
      </StyledModalContainer>
    </StyledModal>
  );
};

export default AddModal;
