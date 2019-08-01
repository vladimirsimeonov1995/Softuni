package retake.instagraph.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.common.Constants;
import retake.instagraph.domain.dtos.UserImportDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.domain.entities.User;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.repository.UserRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static String USER_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/users.json";;
    private final static String USER_EXPORT_STRING_FORMAT = "%d. %s - %d posts";
    private final static String POST_EXPORT_TEXT = " Post Details:";
    private final static String POST_CAPTION_EXPORT_TEXT_FORMAT = "  -Caption: %s";
    private final static String POST_PICTURE_EXPORT_TEXT_FORMAT = "  -Picture: %s";
    private final static String PICTURE_NAME_REGEX_SPLIT = "/";

    private final UserRepository userRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Boolean usersAreImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readUsersFromJson() throws IOException {
        return this.fileUtil.readFile(USER_JSON_FILE_PATH);
    }

    @Override
    public String importUsers(String users) {

        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(users, UserImportDto[].class))
                .forEach(userImportDto -> {

                    Picture pictureEntity = this.pictureRepository.findByPath(userImportDto.getProfilePicture()).orElse(null);

                    if (!this.validationUtil.isValid(userImportDto) || (pictureEntity == null)){
                        importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                        return;
                    }

                    User userEntity = this.userRepository.findByUsername(userImportDto.getUsername()).orElse(null);
                    if (userEntity != null){
                        importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                        return;
                    }

                    userEntity = this.modelMapper.map(userImportDto, User.class);
                    userEntity.setProfilePicture(pictureEntity);

                    this.userRepository.saveAndFlush(userEntity);
                    importResult.append(String.format(
                            Constants.SUCCESSFUL_IMPORT_MESSAGE,
                            userEntity.getClass().getSimpleName(),
                            userEntity.getUsername())
                    )
                            .append(System.lineSeparator());

                });


        return importResult.toString().trim();
    }

    @Override
    public String exportUsersWithTheirPosts() {

        StringBuilder exportResult = new StringBuilder();

        List<User> users = this.userRepository.exportUsersWithPosts();

        users.forEach(user -> {
            exportResult.append(
                    String.format(
                            USER_EXPORT_STRING_FORMAT,
                            users.indexOf(user) + 1,
                            user.getUsername(),
                            user.getPosts().size())
            )
                    .append(System.lineSeparator());

            user.getPosts().forEach(post -> {

                String[] pictureName = post.getPicture().getPath().split(PICTURE_NAME_REGEX_SPLIT);

                exportResult.append(POST_EXPORT_TEXT).append(System.lineSeparator());
                exportResult.append(String.format(POST_CAPTION_EXPORT_TEXT_FORMAT, post.getCaption())).append(System.lineSeparator());
                exportResult.append(String.format(POST_PICTURE_EXPORT_TEXT_FORMAT, pictureName[pictureName.length - 1]))
                        .append(System.lineSeparator());
            });
        });



        return exportResult.toString().trim();
    }
}
