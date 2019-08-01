package retake.instagraph.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.common.Constants;
import retake.instagraph.domain.dtos.posts.PostImportRootDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.domain.entities.Post;
import retake.instagraph.domain.entities.User;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.repository.PostRepository;
import retake.instagraph.repository.UserRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;
import retake.instagraph.util.XmlParser;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.util.Arrays;


@Service
public class PostServiceImpl implements PostService {

    private final static String POST_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/posts.xml";;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Override
    public Boolean postsAreImported() {
        return this.postRepository.count() > 0;
    }

    @Autowired
    public PostServiceImpl(PostRepository postRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, UserRepository userRepository, PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String readPostFromXmlFile() throws IOException {
        return this.fileUtil.readFile(POST_XML_FILE_PATH);
    }

    @Override
    public String importPosts(String posts) throws JAXBException {
        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.xmlParser.parseXml(PostImportRootDto.class, posts).getPostImportDtos())
              .forEach(postImportDto -> {

                  if (!this.validationUtil.isValid(postImportDto)){
                      importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                      return;
                  }

                  User userEntity = this.userRepository.findByUsername(postImportDto.getUsername()).orElse(null);
                  Picture pictureEntity = this.pictureRepository.findByPath(postImportDto.getPicturePath()).orElse(null);

                  if(userEntity == null || pictureEntity == null){
                      importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());

                      return;
                  }

                  Post postEntity = this.modelMapper.map(postImportDto, Post.class);
                  postEntity.setUser(userEntity);
                  postEntity.setPicture(pictureEntity);

                  this.postRepository.saveAndFlush(postEntity);

                  importResult.append(String.format(
                          Constants.SUCCESSFUL_IMPORT_MESSAGE,
                          postEntity.getClass().getSimpleName(),
                          postEntity.getCaption())
                  )
                          .append(System.lineSeparator());
              });

        return importResult.toString().trim();
    }

}
