import 'package:woojoo/common/context_extension.dart';
import 'package:woojoo/common/widget/avatar/w_user_avatar.dart';
import 'package:woojoo/common/widget/w_game_badge.dart';
import 'package:woojoo/common/widget/w_text2.dart';
import 'package:woojoo/common/widget/w_subject_title.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class AcquaintanceScreen extends StatelessWidget {
  AcquaintanceScreen({Key? key}) : super(key: key);
  final List<dynamic> _myFriends = Get.arguments['friends'];
  final String kinds = Get.arguments['kinds'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: context.appColors.mainBackgroundColor,
      appBar: AppBar(
        elevation: 0.0,
        backgroundColor: context.appColors.headerBackgroundColor,
        title: Text(
          kinds,
          style: TextStyle(
              fontSize: 22,
              fontWeight: FontWeight.w400,
              color: context.appColors.text),
        ),
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(
              height: 8,
            ),
            Padding(
              padding:
                  const EdgeInsets.only(right: 13.0, left: 13.0, bottom: 15),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const SubjectTitle("내 게임 친구"),
                  SubjectTitle('${_myFriends.length.toString()} 명'),
                ],
              ),
            ),
            ListView.builder(
                shrinkWrap: true,
                itemExtent: 75.0,
                physics: const NeverScrollableScrollPhysics(),
                itemCount: _myFriends.length,
                itemBuilder: (BuildContext context, int index) {
                  return InkWell(
                    onTap: () {
                      Get.toNamed('/users/${_myFriends[index]['id']}');
                    },
                    child: Padding(
                      padding: const EdgeInsets.only(right: 13.0, left: 13.0),
                      child: Row(
                        children: [
                          UserAvatar(
                            imagePath: _myFriends[index]["profile_image_name"],
                          ),
                          const SizedBox(
                            width: 13,
                          ),
                          Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text2(text: _myFriends[index]["name"], size: 18),
                              GameBadge(
                                size: _myFriends[index]['games'].length,
                                gameList: _myFriends[index]['games'],
                              ),
                            ],
                          ),
                        ],
                      ),
                    ),
                  );
                }),
          ],
        ),
      ),
    );
  }
}
