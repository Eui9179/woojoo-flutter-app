import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:velocity_x/velocity_x.dart';
import 'package:woojoo/common/context_extension.dart';
import 'package:woojoo/common/widget/w_game_badge.dart';
import 'package:woojoo/data/dto/dto_friend_in_list.dart';

import '../theme/font_size.dart';
import 'avatar/w_user_avatar.dart';
import 'w_width.dart';

class FriendInListWidget extends StatelessWidget {
  final UserInList friendSimple;

  const FriendInListWidget({super.key, required this.friendSimple});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () => Get.toNamed(
        '/users/${friendSimple.userSimple.id}',
      ),
      child: Row(
        children: [
          UserAvatar(
            imagePath: friendSimple.userSimple.profileImageName,
          ),
          const Width(13),
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              friendSimple.userSimple.name.text
                  .size(FontSize.normal)
                  .color(context.appColors.text)
                  .make(),
              GameBadge(gameList: friendSimple.gameList),
            ],
          ),
        ],
      ).pSymmetric(h: 13),
    );
  }
}
