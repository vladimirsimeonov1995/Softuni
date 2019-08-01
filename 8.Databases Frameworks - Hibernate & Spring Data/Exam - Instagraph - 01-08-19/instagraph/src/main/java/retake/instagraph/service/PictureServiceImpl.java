package retake.instagraph.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.common.Constants;
import retake.instagraph.domain.dtos.PictureImportDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;

import java.io.IOException;
import java.util.Arrays;


@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURE_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/pictures.json";
    private static final String PICTURE_IMPORT_MESSAGE_PART_1 = "Picture with path:%s";
    private static final String PICTURE_IMPORT_MESSAGE_PART_2 = "and size:%s";
    private static final Double MINIMUM_PICTURE_SIZE_EXPORT = 30000.0;
    private final static String PICTURE_NAME_REGEX_SPLIT = "/";
    private final static String PICTURE_EXPORT_FORMAT = "%s – %.2f";

    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean picturesAreImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromJsonFile() throws IOException {
        return this.fileUtil.readFile(PICTURE_JSON_FILE_PATH);
    }

    @Override
    public String importPictures(String pictures) {

        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(pictures, PictureImportDto[].class))
                .forEach(pictureImportDto -> {
                    if (!this.validationUtil.isValid(pictureImportDto)) {
                        importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                        return;
                    }

                    Picture pictureEntity = this.modelMapper.map(pictureImportDto, Picture.class);
                    this.pictureRepository.saveAndFlush(pictureEntity);

                    importResult.append(String.format(
                            Constants.SUCCESSFUL_IMPORT_MESSAGE,
                            String.format(PICTURE_IMPORT_MESSAGE_PART_1, pictureEntity.getPath()),
                            String.format(PICTURE_IMPORT_MESSAGE_PART_2, pictureEntity.getSize()))
                    )
                                .append(System.lineSeparator());
        });

        return importResult.toString().trim();
    }

    @Override
    public String exportPictures() {

        StringBuilder exportResult = new StringBuilder();

        this.pictureRepository.findBySizeAfterOrderBySizeAsc(MINIMUM_PICTURE_SIZE_EXPORT)
                .forEach(picture -> {
                    String[] pictureName = picture.getPath().split(PICTURE_NAME_REGEX_SPLIT);
                    exportResult.append(
                            String.format(
                                    PICTURE_EXPORT_FORMAT,
                                    pictureName[pictureName.length - 1],
                                    picture.getSize()
                            )
                    )
                            .append(System.lineSeparator());
                });

        return exportResult.toString().trim();
    }
}
