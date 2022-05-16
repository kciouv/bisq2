/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.desktop.primary.main.content.dashboard;

import bisq.desktop.common.utils.ImageUtil;
import bisq.desktop.common.view.Navigation;
import bisq.desktop.common.view.NavigationTarget;
import bisq.desktop.common.view.TabViewChild;
import bisq.desktop.common.view.View;
import bisq.i18n.Res;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashboardView extends View<VBox, DashboardModel, DashboardController> implements TabViewChild {
    private static final int HORIZONTAL_MARGIN = 42;
    private static final int VERTICAL_MARGIN = 24;
    
    public DashboardView(DashboardModel model, DashboardController controller) {
        super(new VBox(), model, controller);
        
       /* Label contentLabel = new Label(Res.get("social.start.content"));
        contentLabel.getStyleClass().addAll("bisq-text-2", "wrap-text");
        contentLabel.setMaxWidth(400);
        contentLabel.setMinHeight(100);
        contentLabel.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(contentLabel);*/
        
        Label gettingStartedLabel = new Label(Res.get("social.start.howToGetStarted"));
        gettingStartedLabel.getStyleClass().add("bisq-text-3");
        
        HBox headerBox = new HBox(gettingStartedLabel);
        headerBox.setPadding(new Insets(0, 0, VERTICAL_MARGIN, 0));
        headerBox.getStyleClass().addAll("border-bottom");
        
        VBox leftBox = getWidgetBox(
                "welcome-community",
                Res.get("social.start.explore.headline"),
                Res.get("social.start.explore.content"),
                Res.get("social.start.explore.button"),
                () -> Navigation.navigateTo(NavigationTarget.SATOSHI_SQUARE)
        );
        leftBox.getStyleClass().add("border-right");
        
        VBox rightBox = getWidgetBox(
                "welcome-profile",
                Res.get("social.start.newOffer.headline"),
                Res.get("social.start.newOffer.content"),
                Res.get("social.start.newOffer.button"),
                controller::showNewProfilePopup
        );

        HBox contentBox = new HBox(leftBox, rightBox);
        contentBox.setPadding(new Insets(0, -HORIZONTAL_MARGIN, 0, -HORIZONTAL_MARGIN));

        VBox gridPane = new VBox(headerBox, contentBox);
        gridPane.getStyleClass().addAll("bisq-box-1");
        gridPane.setPadding(new Insets(VERTICAL_MARGIN, HORIZONTAL_MARGIN, 2 * VERTICAL_MARGIN, HORIZONTAL_MARGIN));
        root.getChildren().add(gridPane);
    }
    
    private VBox getWidgetBox(String imageId, String headline, String content, String buttonLabel, Runnable onAction) {
        Label headlineLabel = new Label(headline, ImageUtil.getImageViewById(imageId));
        headlineLabel.setGraphicTextGap(16.0);
        headlineLabel.setMaxWidth(284);
        headlineLabel.getStyleClass().addAll("bisq-text-headline-2",  "wrap-text");
        
        Label contentLabel = new Label(content);
        contentLabel.getStyleClass().addAll("bisq-text-3", "wrap-text");
        contentLabel.setMaxWidth(272);
        contentLabel.setMinHeight(80);
        contentLabel.setAlignment(Pos.TOP_LEFT);

        Button button = new Button(buttonLabel);
        button.getStyleClass().add("bisq-big-green-button");
        button.setOnAction(e -> onAction.run());
        button.setPrefWidth(360);
        
        VBox box = new VBox(16, headlineLabel, contentLabel, button);
        box.setPadding(new Insets(VERTICAL_MARGIN, HORIZONTAL_MARGIN, 0, HORIZONTAL_MARGIN));
        box.setMinWidth(360);
        box.setPrefWidth(360);
        HBox.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    @Override
    protected void onViewAttached() {}

    @Override
    protected void onViewDetached() {}

    @Override
    public void applyPadding(Region root) {
        root.getStyleClass().add("bisq-content-bg");
        root.setPadding(new Insets(16, 67, 0, 0));
    }
}
